import request from '@/utils/request'

export function getCities(){
    return request({
        url: '/api/city/',
        method: 'get'
    })
}

export function createCity(data){
    return request({
        url: '/api/city/',
        method: 'post',
        data: data
    })
}

export function editCity(data){
    return request({
        url: '/api/city/',
        method: 'put',
        data: data
    })
}
