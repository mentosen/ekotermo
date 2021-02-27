import request from '@/utils/request'

export function createBuilding(data){
    return request({
        url: '/api/building/',
        method: 'post',
        data: data
    })
}

export function editBuilding(data){
    return request({
        url: '/api/building/',
        method: 'put',
        data: data
    })
}
