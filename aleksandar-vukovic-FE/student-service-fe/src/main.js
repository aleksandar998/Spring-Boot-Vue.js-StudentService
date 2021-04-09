import { createApp, reactive } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import "bootstrap"
import "bootstrap/dist/css/bootstrap.min.css"
import axios from "axios";
import VueAxios from "vue-axios";
import {BootstrapIconsPlugin} from "bootstrap-icons-vue";

const GStore = reactive({
    toastMessage: null
})

createApp(App)
    .use(store)
    .use(router)
    .use(VueAxios, axios)
    .use(BootstrapIconsPlugin)
    .provide('GStore', GStore)
    .mount('#app')
