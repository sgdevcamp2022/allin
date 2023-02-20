import { rest } from 'msw'
import { chatHandlers } from './chat.handlers'

export const handlers = [
  rest.post(import.meta.env.VITE_AUTH_SERVER_URL + '/api/v1/auth/login', async (req, res, ctx) => {
    const userData = await req.json()

    if (userData.email === 'asdf@asdf.com' && userData.password === 'asdf')
      return res(
        ctx.status(201),
        ctx.json({
          grantType: 'bearer',
          accessToken:
            'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTY3NTc1MzQ5OX0.aTAjLRTY1Y47-PoX8_J4frxneJBxEQzvdhA4q0Eg738v32LAfoM4Ga_p_2vQTo3I_aJ-NxRpuPUtTjpku9gikw',
          refreshToken:
            'eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NzYzNTY0OTl9.Y2gnlkC7gRIOxqPUWHbcVxK-6RX-ag_cQg8OkZiEST2DbxfpISCjERA8l-2PY9piBhuUPxgE_mn0u2ryx1Qkog',
          accessTokenExpiresIn: 1675753499984,
        })
      )
    return res(
      ctx.status(401),
      ctx.json({
        timestamp: '2023-02-07T06:40:57.205+00:00',
        code: '4001000',
        error: 'RuntimeException',
        message: '토큰의 유저 정보가 일치하지 않습니다.',
      })
    )
  }),
  rest.post(import.meta.env.VITE_AUTH_SERVER_URL + '/api/v1/auth/reissue', (req, res, ctx) => {
    return res(
      ctx.status(201),
      ctx.json({
        grantType: 'bearer',
        accessToken:
          'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTY3NTc1Mzc3MX0.NuGdIGk55y1Bc2nLIEk0M6jYS3hX_iEReZrdYTD0Puafv3x6FzMF2abLL9t5pkJhui1bU7-rvGOxXLgTwJ7qVg',
        refreshToken:
          'eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NzYzNTY3NzF9.8_0e6NKzjkY5L_qCWWw8gRceomwQRQodsds_YEErFkOhsfWUqTOuN0Kf6_aVJotqcrcSjFAjg4FbPZfWSt6tNw',
        accessTokenExpiresIn: 1675753771916,
      })
    )
  }),
  rest.post(import.meta.env.VITE_AUTH_SERVER_URL + '/api/v1/auth/signup', async (req, res, ctx) => {
    const userData = await req.json()

    if (userData.nickName !== 'loopy') {
      return res(
        ctx.status(401),
        ctx.json({
          timestamp: '2023-02-07T06:34:02.698+00:00',
          code: '4001000',
          error: 'RuntimeException',
          message: '이미 가입되어 있는 이메일입니다.',
        })
      )
    }
    return res(
      ctx.status(201),
      ctx.json({
        email: '1234@naver.com',
        userName: 'hayan',
        nickName: 'dd',
        authorities: [
          {
            authorityStatus: 'ROLE_USER',
          },
        ],
      })
    )
  }),
  ...chatHandlers,
]
