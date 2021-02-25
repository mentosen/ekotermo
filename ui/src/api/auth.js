import request from '@/utils/request'

export function signup(data){
    return request({
        url: '/api/auth/signup',
        method: 'post',
        data: data
    })
}

export function signupcompany(data){
    return request({
        url: '/api/auth/signupcompany',
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

export function isAccessCodeExists(data){
    return request({
        url: '/api/auth/accesscode',
        method: 'post',
        data: data
    })
}

export function isSerialNumberExists(data){
    return request({
        url: '/api/auth/serialnumber',
        method: 'post',
        data: data
    })
}
