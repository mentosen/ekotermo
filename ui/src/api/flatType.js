import request from '@/utils/request'

export function findAllFlatTypes(buildingId){
    return request({
        url: '/api/flat-type/',
        method: 'get',
        params: {'buildingId': buildingId}
    })
}

export function createFlatType(file){
    return request({
        url: '/api/flat-type/',
        method: 'post',
        data: file
    })
}

export function editFlatType(data){
    return request({
        url: '/api/flat-type/',
        method: 'put',
        data: data
    })
}
