<template>

    <v-card outlined>
        <v-card-title>
            DeliveryCount # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <String label="OrderId" v-model="item.orderId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Count" v-model="item.count" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="RiderId" v-model="item.riderId" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'DeliveryCountViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/deliveryCounts/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>

