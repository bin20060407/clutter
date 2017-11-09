<template>
    <div>
        <el-radio-group>
            <el-radio-button v-for="attr in attrOptions" :key="attr.id" :label="attr.id">{{attr.name}}
            </el-radio-button>
        </el-radio-group>
    </div>
</template>
<script>
export default {
    name: 'test',
    data() {
        return {
            attrOptions: []
        };
    },
    created() {
        this.getApiData();
    },
    methods: {
        // ajax get
        getApiData() {
            let me = this;
            setTimeout(function() {
                me.attrOptions = [
                    {
                        id: 1,
                        name: '北京'
                    },
                    {
                        id: 2,
                        name: '上海'
                    },
                    {
                        id: 3,
                        name: '广州'
                    },
                    {
                        id: 4,
                        name: '武汉'
                    }
                ];
            }, 500);
        }
    }
};
</script>
