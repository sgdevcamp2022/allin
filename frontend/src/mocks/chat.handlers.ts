import { rest } from 'msw'
export const chatHandlers = [
  rest.get(import.meta.env.VITE_CHAT_SERVER_URL + '/api/v1/chats/', async (req, res, ctx) => {
    return res(
      ctx.status(201),
      ctx.json({
        result: 'success',
        data: [
          {
            sender: '마틴파울러',
            content: 'hello!!',
          },
          {
            sender: '최범균',
            content: '도메인 주도 개발 시작하기 읽어보세요..',
          },
          {
            sender: '마틴파울러',
            content: 'hello!!',
          },
          {
            sender: '마틴파울러',
            content: 'hello!!',
          },
          {
            sender: '마틴파울러',
            content: 'hello!!',
          },
          {
            sender: '마틴파울러',
            content: 'hello!!',
          },
          {
            sender: '마틴파울러',
            content: 'hello!!',
          },
          {
            sender: '마틴파울러',
            content: 'hello!!',
          },
          {
            sender: '마틴파울러',
            content: 'hello!!',
          },
          {
            sender: '마틴파울러',
            content: 'hello!!',
          },
          {
            sender: '마틴파울러',
            content: 'hello!!',
          },
          {
            sender: '마틴파울러',
            content: 'hello!!',
          },
          {
            sender: '마틴파울러',
            content: 'hello!!',
          },
          {
            sender: '마틴파울러',
            content: 'hello!!',
          },
          {
            sender: '마틴파울러',
            content: 'hello!!',
          },
          {
            sender: '마틴파울러',
            content: 'hello!!',
          },
          {
            sender: '마틴파울러',
            content: 'hello!!',
          },
          {
            sender: '마틴파울러',
            content: 'hello!!',
          },
          {
            sender: '마틴파울러',
            content: 'hello!!',
          },
        ],
      })
    )
  }),
]
