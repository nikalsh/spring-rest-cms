<template>
  <div class="asd">
    <p>{{ email }}</p>
    <img :src="img">
    <br>
    USERPANEL
  </div>
</template>

<script>

export default {
    name: 'UserPanel',
    // props:{
    //     email: {
    //         default: 'asd',
    //         type: String
    //
    //     }
    // },

    data() {
        return {
            email: this.$globalUserDetails.email,
            img: this.$globalUserDetails.img,
            name: this.$globalUserDetails.name,
        }

    },





    methods: {

    },


}


</script>

<style scoped>
.asd {
  background-color: lightgray;
}
</style>
