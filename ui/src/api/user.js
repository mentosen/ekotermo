import request from '@/utils/request'

export function findDuplicate(data){
    return request({
        url: '/api/user/duplicate',
        method: 'get',
        params: {'name': data.login, 'email': data.email, 'phone': data.phone,}
    })
}

export function getUserInfo(){
    return request({
        url: '/api/user/getInfo',
        method: 'get',
        params: {}
    })
}