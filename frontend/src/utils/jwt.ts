type accessTokenPayload = { sub: number; auth: string; exp: number }
type refreshTokenPayload = { exp: number }

class JWTUtil {
  public static isAuth(token: string): boolean {
    const payload = token.split('.')[1]

    if (!payload || payload === '') return false
    const payloadJSON = JSON.parse(btoa(encodeURIComponent(payload))) as
      | accessTokenPayload
      | refreshTokenPayload
    return payloadJSON.exp > Math.floor(new Date().getTime() / 1000.0)
  }
}

export default JWTUtil
