import request from '@/utils/request'

export function findAllFlatTypes(buildingId){
    return request({
        url: '/api/flat-type/',
        method: 'get',
        params: {'buildingId': buildingId}
    })
}

export function createFlatType(data){
    return request({
        url: '/api/flat-type/',
        method: 'post',
        data: data
    })
}

export function editFlatType(data){
    return request({
        url: '/api/flat-type/',
        method: 'put',
        data: data
    })
}
