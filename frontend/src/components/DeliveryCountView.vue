<template>

    <v-data-table
        :headers="headers"
        :items="deliveryCount"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'DeliveryCountView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
                { text: "orderId", value: "orderId" },
                { text: "count", value: "count" },
                { text: "riderId", value: "riderId" },
            ],
            deliveryCount : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/deliveryCounts'))

            temp.data._embedded.deliveryCounts.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.deliveryCount = temp.data._embedded.deliveryCounts;
        },
        methods: {
        }
    }
</script>

