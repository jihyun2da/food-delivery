
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import OrderManager from "./components/listers/OrderCards"
import OrderDetail from "./components/listers/OrderDetail"
import PaymentManager from "./components/listers/PaymentCards"
import PaymentDetail from "./components/listers/PaymentDetail"

import StoreOrderManager from "./components/listers/StoreOrderCards"
import StoreOrderDetail from "./components/listers/StoreOrderDetail"

import OrderListView from "./components/OrderListView"
import OrderListViewDetail from "./components/OrderListViewDetail"
import DeliveryManager from "./components/listers/DeliveryCards"
import DeliveryDetail from "./components/listers/DeliveryDetail"

import DeliveryCountView from "./components/DeliveryCountView"
import DeliveryCountViewDetail from "./components/DeliveryCountViewDetail"
import NotificationLogManager from "./components/listers/NotificationLogCards"
import NotificationLogDetail from "./components/listers/NotificationLogDetail"

import OrderStatusView from "./components/OrderStatusView"
import OrderStatusViewDetail from "./components/OrderStatusViewDetail"

export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/orders',
                name: 'OrderManager',
                component: OrderManager
            },
            {
                path: '/orders/:id',
                name: 'OrderDetail',
                component: OrderDetail
            },
            {
                path: '/payments',
                name: 'PaymentManager',
                component: PaymentManager
            },
            {
                path: '/payments/:id',
                name: 'PaymentDetail',
                component: PaymentDetail
            },

            {
                path: '/storeOrders',
                name: 'StoreOrderManager',
                component: StoreOrderManager
            },
            {
                path: '/storeOrders/:id',
                name: 'StoreOrderDetail',
                component: StoreOrderDetail
            },

            {
                path: '/orderLists',
                name: 'OrderListView',
                component: OrderListView
            },
            {
                path: '/orderLists/:id',
                name: 'OrderListViewDetail',
                component: OrderListViewDetail
            },
            {
                path: '/deliveries',
                name: 'DeliveryManager',
                component: DeliveryManager
            },
            {
                path: '/deliveries/:id',
                name: 'DeliveryDetail',
                component: DeliveryDetail
            },

            {
                path: '/deliveryCounts',
                name: 'DeliveryCountView',
                component: DeliveryCountView
            },
            {
                path: '/deliveryCounts/:id',
                name: 'DeliveryCountViewDetail',
                component: DeliveryCountViewDetail
            },
            {
                path: '/notificationLogs',
                name: 'NotificationLogManager',
                component: NotificationLogManager
            },
            {
                path: '/notificationLogs/:id',
                name: 'NotificationLogDetail',
                component: NotificationLogDetail
            },

            {
                path: '/orderStatuses',
                name: 'OrderStatusView',
                component: OrderStatusView
            },
            {
                path: '/orderStatuses/:id',
                name: 'OrderStatusViewDetail',
                component: OrderStatusViewDetail
            },


    ]
})
