<template>
    <div class="wrapper">
        <v-head></v-head>
        <v-sidebar></v-sidebar>
        <div class="content-box" :class="{'content-collapse':collapse}">
            <div class="content">
                <transition name="move" mode="out-in">
                    <keep-alive :include="tagsList">
                        <router-view></router-view>
                    </keep-alive>
                </transition>
            </div>
        </div>
    </div>
</template>

<script>
import vHead from './Header.vue'
import vSidebar from './Bar.vue'
export default {
  data () {
    return {
      tagsList: [],
      collapse: false
    }
  },
  components: {
    vHead, vSidebar
  },
  created () {

    // 只有在标签页列表里的页面才使用keep-alive，即关闭标签之后就不保存到内存中了。
  }
}
</script>
