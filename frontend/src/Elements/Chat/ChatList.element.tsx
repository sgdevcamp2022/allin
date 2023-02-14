import { useRecoilState } from 'recoil'
import { chatListState } from '../../Atoms/Chat/Chat.atoms'
import ChatElement from './Chat.element'
import ChatSenderElement from './ChatSender.element'
import { useEffect, useState } from 'react'
import ChatServer from '../../utils/ChatServer'
import axios from 'axios'
import ChatReportPopupElement from '../Popup/ChatReportPopup.element'

type chatPageResponse = {
  result: string
  data: [{ sender: string; content: string }]
}

const ChatListElement = () => {
  const [chatList, setChatList] = useRecoilState(chatListState)
  const [chatPage, setChatPage] = useState(0)

  const getPrevChats = async () => {
    try {
      const res = await axios.get(
        `${import.meta.env.VITE_CHAT_SERVER_URL}/api/v1/chats/?page=${chatPage}`
      )
      if (res.status !== 201) {
        throw new Error(res.data)
      }
      const chatData = res.data as chatPageResponse

      if (chatData.result !== 'success') {
        throw new Error(chatData.result)
      }

      const resChatList = chatData.data
      setChatList([...resChatList, ...chatList])
    } catch (err) {
      alert('예기치 못한 오류가 발생햇습니다')
      console.error(err)
    } finally {
      setChatPage(chatPage + 1)
    }
  }

  useEffect(() => {
    const chatListElement = document.querySelector('.chatList') as HTMLDivElement
    chatListElement.scrollTop = chatListElement?.scrollHeight
  }, [chatList])

  useEffect(() => {
    getPrevChats()
  }, [])

  // const chatServer = ChatServer.getInstance()
  // chatServer.getServer()
  // chatServer.setChannal('channalName', () => {})

  return (
    <div className="border-border border-[1px] rounded-2xl p-6">
      <div className="overflow-x-hidden h-[58vh] mb-4 chatList scrollbar-hide">
        {chatList.map((chat) => (
          <ChatElement chat={chat} className="w-full" />
        ))}
      </div>
      <ChatSenderElement />
    </div>
  )
}

export default ChatListElement
