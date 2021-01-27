import request from '@/utils/request'

export function signup(data){
    return request({
        url: '/api/auth/signup',
        method: 'post',
        data: data
    })
}

export function signin(data){
    return request({
        url: '/api/auth/signin',
        method: 'post',
        data: data
    })
}