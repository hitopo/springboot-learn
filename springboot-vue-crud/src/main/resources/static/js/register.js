//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

// Vue实例
new Vue({
    el: '#app',
    data() {
        return {
            checked: false,
            register: {
                username: '',
                password: '',
                repassword: ''
            }
        }
    },
    methods: {
        submitForm(register) {
            if (this.register.repassword !== this.register.password) {
                // 弹出错误信息框
                this.$emit(
                    'submit-form',
                    this.$message({
                        message: '两次输入的密码不相同',
                        type: 'warning',
                        duration: 6000
                    }),
                );
            } else {
                this.$refs[register].validate((valid) => {
                    if (valid) {
                        //提交表单
                        // TODO：contextPath
                        this.$http.post('/simple-shop/register', {
                            username: this.register.username,
                            password: this.register.password,
                        }).then(result => {
                            if (result.body.code === 201) {
                                // 跳转到登录界面
                                window.location.href = "/simple-shop/login";
                                this.$message({
                                    message: '用户注册成功',
                                    type: 'success',
                                    duration: 6000
                                })
                            } else {
                                // 弹出错误信息框
                                this.$emit(
                                    'submit-form',
                                    this.$message({
                                        message: result.body.msg,
                                        type: 'warning',
                                        duration: 6000
                                    }),
                                );
                            }
                        });
                    } else {
                        this.$emit(
                            'submit-form',
                            this.$message({
                                message: '请完善信息后再注册！',
                                type: 'warning',
                                duration: 6000
                            }),
                        );
                        return false;
                    }
                });
            }
        },
        registerEnter(register) {
            this.submitForm(register);
        }
    }
});
