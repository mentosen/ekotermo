import Cookies from 'js-cookie'

const TokenKey = 'ev_token';

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  if(process.env.NODE_ENV !== 'production'){
    return Cookies.set(TokenKey, token)
  } else {
    return Cookies.set(TokenKey, token, {secure: true, domain: '.eventssion.com'})
  }
}

export function removeToken() {
  Cookies.remove(TokenKey, {secure: true, domain: '.eventssion.com'});
  Cookies.remove(TokenKey);
}
