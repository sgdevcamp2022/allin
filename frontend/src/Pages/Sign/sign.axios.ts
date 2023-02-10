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
  setIsWrongLogin: Dispatch<SetStateAction<boolean>>
) => {
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
    localStorage.setItem('accessToken', userData.accessToken)
    localStorage.setItem('refreshToken', userData.refreshToken)
  } catch (err) {
    alert('예기치 못한 에러가 있습니다.')
    console.dir(err)

    setIsWrongLogin(true)
    return
  }
}

const getRefreshToken = async () => {
  let accessToken = localStorage.getItem('accessToken') ?? ''
  let refreshToken = localStorage.getItem('refreshToken') ?? ''

  if (!JWTUtil.isAuth(accessToken)) {
    try {
      const res = (await Axios.post(import.meta.env.VITE_AUTH_SERVER_URL + '/api/v1/auth/reissue', {
        accessToken,
        refreshToken,
      })) as LoginSuccessResponse
      localStorage.setItem('accessToken', res.accessToken)
      localStorage.setItem('refreshToken', res.refreshToken)
      accessToken = res.accessToken
      refreshToken = res.refreshToken
    } catch (err) {
      console.error('[_axios.sign.request] jwt : ' + err)
      localStorage.setItem('accessToken', '')
      localStorage.setItem('refreshToken', '')
    }
  }
}

export { signInAxios }
