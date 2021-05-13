import request from '@/utils/request'

export function getRegions(){
    return request({
        url: '/api/region/',
        method: 'get'
    })
}

export function createRegion(data){
    return request({
        url: '/api/region/',
        method: 'post',
        data: data
    })
}

export function editRegion(data){
    return request({
        url: '/api/region/',
        method: 'put',
        data: data
    })
}
