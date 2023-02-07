import * as Stomp from '@stomp/stompjs'

class ChatServer {
  private static instance: ChatServer
  private static stompServer: Stomp.CompatClient
  private static topicId: string

  private constructor() {}

  public static getInstance(): ChatServer {
    if (!ChatServer.instance) {
      ChatServer.instance = new ChatServer()
    }
    return ChatServer.instance
  }

  public getServer() {
    if (!ChatServer.stompServer) {
      ChatServer.stompServer = Stomp.Stomp.client(`${process.env.CHAT_SERVER_URL}/ws`)
    }
    if (ChatServer.stompServer.active) {
      alert('채팅서버에 연결할 수 없습니다.')
    }
  }

  public setChannal(topicId: string, callBack: Stomp.messageCallbackType) {
    ChatServer.stompServer.subscribe(`/message/${topicId}`, (data) => {
      callBack(JSON.parse(data.body))
    })
    ChatServer.topicId = topicId
  }

  public sendContent(sender: string, content: string) {
    // const rappingMessage = {sender, content}
    const rappingMessage = { content }
    ChatServer.stompServer.send(
      `/message/${ChatServer.topicId}/send`,
      {},
      JSON.stringify(rappingMessage)
    )
  }
}
