import request from '@/utils/request'

export function getCompanyByUser(){
    return request({
        url: '/api/company/getCompany',
        method: 'get'
    })
}

export function getSimpleCompanyByUser(){
    return request({
        url: '/api/company/getCompanyId',
        method: 'get'
    })
}

export function saveCompany(data){
    return request({
        url: '/api/company/',
        method: 'post',
        data: data
    })
}

export function updateCompany(data){
    return request({
        url: '/api/company/',
        method: 'put',
        data: data
    })
}


