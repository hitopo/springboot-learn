//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

// Vue实例
new Vue({
    el: '#app',
    data() {
        return {
            checked: false,
            login: {
                username: '',
                password: '',
                remember: ''
            },
            flag: true,
        };
    },
    methods: {
        /**
         * 提交登录表单
         */
        submitForm(login) {
            this.$refs[login].validate((valid) => {
                if (valid) {
                    //提交表单
                    // TODO:目前不知道怎么在这里面获得simple-shop的项目路径，先硬编码
                    this.$http.post('/simple-shop/user/login', {
                        username: this.login.username,
                        password: this.login.password,
                    }).then(result => {
                        if (result.body.code === 200) {
                            // 登录成功，跳转到首页
                            // TODO: 硬编码contextPath
                            window.location.href = "/simple-shop/index";
                        } else {
                            // 弹出错误信息框
                            this.$emit(
                                'submit-form',
                                this.$message({
                                    message: '用户名或密码错误！',
                                    type: 'warning',
                                    duration: 6000
                                }),
                            );
                        }
                    });
                } else {
                    // 前端验证没有通过，弹出弹窗
                    this.$emit(
                        'submit-form',
                        this.$message({
                            message: '输入信息有误！',
                            type: 'warning',
                            duration: 6000
                        }),
                    );
                    return false;
                }
            });
        },
        loginEnter(login) {
            this.submitForm(login);
        },

    }
});