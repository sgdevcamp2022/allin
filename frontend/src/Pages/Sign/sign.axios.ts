import { Dispatch, SetStateAction } from 'react'
import Axios from '../../utils/axios'
import JWTUtil from '../../utils/jwt'

type LoginSuccessResponse = {
  grantType: string
  accessToken: string
  refreshToken: string
  accessTokenExpiresIn: number
}

const signInAxios = async (
  email: string,
  password: string,
  setIsWrongLogin: Dispatch<SetStateAction<boolean>>,
  isAutoLogin: boolean
) => {
  const storage = isAutoLogin ? localStorage : sessionStorage
  localStorage.setItem('isAutoLogin', isAutoLogin ? 'true' : 'false')

  try {
    const res = await Axios.post(import.meta.env.VITE_AUTH_SERVER_URL + '/api/v1/auth/login', {
      email,
      password,
    })

    if (res.status !== 201) {
      setIsWrongLogin(true)
      return
    }

    const userData = res.data as LoginSuccessResponse
    storage.setItem('accessToken', userData.accessToken)
    storage.setItem('refreshToken', userData.refreshToken)
    storage.setItem('isLogined', 'true')

    location.href = '/'
  } catch (err) {
    alert('예기치 못한 에러가 있습니다.')
    console.dir(err)

    setIsWrongLogin(true)
    return
  }
}

const getRefreshToken = async () => {
  const storage = localStorage.getItem('isAutoLogin') === 'true' ? localStorage : sessionStorage
  let accessToken = storage.getItem('accessToken') ?? ''
  let refreshToken = storage.getItem('refreshToken') ?? ''

  if (!JWTUtil.isAuth(accessToken)) {
    try {
      const res = (await Axios.post(import.meta.env.VITE_AUTH_SERVER_URL + '/api/v1/auth/reissue', {
        accessToken,
        refreshToken,
      })) as LoginSuccessResponse
      storage.setItem('accessToken', res.accessToken)
      storage.setItem('refreshToken', res.refreshToken)
      accessToken = res.accessToken
      refreshToken = res.refreshToken
    } catch (err) {
      console.error('[_axios.sign.request] jwt : ' + err)
      storage.setItem('accessToken', '')
      storage.setItem('refreshToken', '')
    }
  }
}

export { signInAxios }
