import request from '@/utils/request'

export function download(id){
    return request({
        url: '/api/image/'+ id,
        method: 'get'
    })
}

export function upload(data, entityId){
    return request({
        url: '/api/image/',
        method: 'post',
        headers: {
            'Content-Type': 'multipart/form-data'
        },
        data: data,
        params: {'entityId': entityId}
    })
}

export function deleteImage(ids){
    return request({
        url: '/api/image/',
        method: 'delete',
        data:ids
    })
}
