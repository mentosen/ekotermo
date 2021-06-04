import request from '@/utils/request'

// export function findAllFlatTypes(buildingId){
//     return request({
//         url: '/api/flat-type/',
//         method: 'get',
//         params: {'buildingId': buildingId}
//     })
// }

export function createFlat(data){
    return request({
        url: '/api/flat/',
        method: 'post',
        data: data
    })
}

export function editFlat(data){
    return request({
        url: '/api/flat/',
        method: 'put',
        data: data
    })
}
