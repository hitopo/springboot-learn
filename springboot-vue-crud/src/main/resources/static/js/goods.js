/**
 * 校外客户信息列表
 */

//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

// Vue实例
var vm = new Vue({
    el: '#app',
    data() {
        return {
            //element-ui的table需要的参数必须是Array类型的
            goods: [{
                id: '',
                title: '',
                price: '',
                imageUrl: '',
                brand: ''
            }],

            //编辑表
            editor: {
                id: 0,
                title: '',
                price: '',
                imageUrl: '',
                brand: ''
            },
            //添加dialog
            showSave: false,
            //编辑dialog
            showEditor: false,

            //条件查询单独封装的对象
            searchEntity: {},

            //checkbox选择的行中所有数据，将会放入multipartSelection数组中
            multipleSelection: [],
            selectIds: [], //被checkbox选择的id值，用于批量删除
            count: 0, //tag栏，此项那是checkbox选择了几行

            //分页选项
            pageConf: {
                //设置一些初始值(会被覆盖)
                pageCode: 1, //当前页
                pageSize: 6, //每页显示的记录数
                totalPage: 12, //总记录数
                pageOption: [6, 10, 20], //分页选项
            },

            loading: {},

            //文件上传的参数
            dialogImageUrl: '',
            dialogVisible: false,
            //图片列表（用于回显图片）
            fileList: [{name: '', url: ''}],
            activeIndex: '2', //默认激活
        }
    },
    methods: {
        /**
         * loading加载动画
         */
        loadings() {
            this.loading = this.$loading({
                lock: true,
                text: '拼命加载中',
                spinner: 'el-icon-loading',
            });
            setTimeout(() => {
                this.loading.close();
            }, 2000);
        },

        /**
         * Public method
         */
        //刷新列表
        reloadList() {
            this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        },
        //条件查询
        search(pageCode, pageSize) {
            this.loadings();
            this.$http.get('/simple-shop/good/goods', {
                params: {
                    pageCode: pageCode,
                    pageSize: pageSize,
                    brand: this.searchEntity.brand,
                    title: this.searchEntity.title
                }
            }).then(result => {
                if (result.body.code !== 200) {
                    console.log(result.body.msg);
                } else {
                    this.goods = result.body.data.dataList;
                    this.pageConf.totalPage = result.body.data.total;
                    this.loading.close(); //数据更新成功就手动关闭动画
                }
            });

        },
        //checkbox复选框
        selectChange(val) {
            this.count = val.length;
            this.multipleSelection = val;
        },
        //清空已选择的
        clearSelect() {
            this.$refs.goods.clearSelection();
        },
        //pageSize改变时触发的函数
        handleSizeChange(val) {
            this.search(this.pageConf.pageCode, val);
        },
        //当前页改变时触发的函数
        handleCurrentChange(val) {
            this.pageConf.pageCode = val; //为了保证刷新列表后页面还是在当前页，而不是跳转到第一页
            this.search(val, this.pageConf.pageSize);
        },
        //更新
        sureEdit(editor) {
            //关闭对话框
            this.showEditor = false;
            //调用更新数据的接口
            this.$http.put('/simple-shop/good/goods/' + this.editor.id, JSON.stringify(this.editor)).then(result => {
                if (result.body.code === 200) {
                    //更新成功
                    this.$message({
                        type: 'success',
                        message: result.body.msg,
                        duration: 6000
                    });
                    //刷新列表
                    this.reloadList();
                    this.goods = [];
                    this.$refs.editor.resetFields();
                } else {
                    //更新失败
                    this.$message({
                        type: 'warning',
                        message: result.body.msg,
                        duration: 6000
                    });
                    //刷新列表
                    this.reloadList();
                    this.$refs.editor.resetFields();
                }
            })
        },
        //删除
        sureDelete(ids) {
            this.$confirm('你确定永久删除商品信息？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                //调用删除的接口(这里必须将数据转换成JSON格式，不然接收不到值，并且后端要用@RequestBody注解标识)
                this.$http.post('/simple-shop/good/goods/delete', JSON.stringify(ids)).then(result => {
                    if (result.body.code === 204) {
                        //删除成功
                        this.selectIds = []; //清空选项
                        this.$message({
                            type: 'success',
                            message: result.body.msg,
                            duration: 6000
                        });
                        //刷新列表
                        //为什么要判断并赋值？
                        //答：即使调用reloadList()刷新列表，但是对于删除，在reloadList()中获取到的totalPage总记录和pageCode当前页都是未删除之前的记录，当遇到删除此页的最后一个记录时，页码会自动跳到上一页，但是table中的数据显示"暂无记录"
                        //   所以要判断，如果是删除此页的最后一条记录，删除后自动跳转到前一页，数据也是前一页的数据
                        if ((this.pageConf.totalPage - 1) / this.pageConf.pageSize === (this.pageConf.pageCode - 1)) {
                            this.pageConf.pageCode = this.pageConf.pageCode - 1;
                        }
                        this.reloadList();
                    } else {
                        //删除失败
                        this.selectIds = []; //清空选项
                        this.$message({
                            type: 'warning',
                            message: result.body.msg,
                            duration: 6000
                        });
                        //刷新列表
                        this.reloadList();
                    }
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除',
                    duration: 6000
                });
            });
        },
        //添加
        save(editor) {
            this.$refs[editor].validate((valid) => {
                if (valid) {
                    //关闭dialog
                    this.showSave = false;
                    //调用保存的接口
                    this.$http.post('/simple-shop/good/goods', JSON.stringify(this.editor)).then(result => {
                        if (result.body.code === 201) {
                            //保存成功
                            this.$message({
                                type: 'success',
                                message: result.body.msg,
                                duration: 6000
                            });
                            //刷新表格
                            this.reloadList();
                            this.editor = {};
                            this.$refs.editor.resetFields();
                        } else {
                            //保存失败
                            this.$emit(
                                'save',
                                this.$message({
                                    type: 'warning',
                                    message: result.body.msg,
                                    duration: 6000
                                }),
                            );
                            //刷新表格
                            this.reloadList();
                            this.editor = {};
                            this.$refs.editor.resetFields();
                        }
                    });
                } else {
                    this.$emit(
                        'save',
                        this.$message({
                            message: '请完善信息之后再添加！',
                            type: 'warning',
                            duration: 6000
                        }),
                    );
                    return false;
                }
            });
        },
        // 点击新增按钮
        saveBtn() {
            //打开新增dialog
            this.showSave = true;
            this.editor = {}; //清空表单
            this.fileList = []; //清空文件列表
            //清空原始数据
            if (this.$refs['editor'] !== undefined) {
                this.$refs['editor'].resetFields(); //经查询：可能是由于对象还没有生成，导致误读了空对象而报错
            }
        },
        //点击了编辑按钮
        handleEdit(id) {
            //打开dialog
            this.showEditor = true;
            this.editor = {}; //清空表单
            //查询当前id对应的数据
            this.$http.get(`/simple-shop/good/goods/${id}`).then(result => {
                if (result.body.code !== 200) {
                    this.$message({
                        message: '获取信息失败',
                        type: 'error',
                        duration: 6000
                    })
                } else {
                    this.fileList.forEach(row => {
                        row.url = result.body.data.imageUrl; //将图片的URL地址赋值给file-list展示出来
                    });
                    this.editor = result.body.data;
                    //移除element-ui表单校验残留
                    this.$refs['editor'].resetFields();
                }
            });
        },
        //删除按钮
        handleDelete(id) {
            var ids = [];
            ids.push(id);
            this.sureDelete(ids);
        },
        //批量删除按钮
        deleteSelect(rows) {
            if (rows) {
                rows.forEach(row => {
                    this.selectIds.push(row.id);
                    this.$refs.goods.toggleRowSelection(row);
                });
                //调用删除方法
                this.sureDelete(this.selectIds);
            } else {
                this.$refs.goods.clearSelection();
            }
        },
        //文件上传成功的钩子函数
        handleSuccess(res, file, fileList) {
            this.$message({
                type: 'info',
                message: '图片上传成功',
                duration: 6000
            });
            this.editor.imageUrl = file.response.data.url; //将返回的图片访问url地址赋值imageUrl字段
            console.log(this.editor.imageUrl);
        },
        //删除文件之前的钩子函数
        handleRemove(file, fileList) {
            this.$message({
                type: 'info',
                message: '已删除原有图片',
                duration: 6000
            });
        },
        //上传的文件个数超出设定时触发的函数
        onExceed(files, fileList) {
            this.$message({
                type: 'info',
                message: '最多只能上传一个图片',
                duration: 6000
            });
        },
        //参数是上传的文件，若返回false，或返回Primary且被reject，则停止上传
        beforeUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isGIF = file.type === 'image/gif';
            const isPNG = file.type === 'image/png';
            const isBMP = file.type === 'image/bmp';
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG && !isGIF && !isPNG && !isBMP) {
                this.$message.error('上传图片必须是JPG/GIF/PNG/BMP 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传图片大小不能超过 2MB!');
            }
            return (isJPG || isBMP || isGIF || isPNG) && isLt2M;
        },
        //点击清除按钮
        clear() {
            this.searchEntity = {};
            this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        }
    },
    // 生命周期函数
    created() {
        this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        this.loadings(); //加载动画
    }
});
