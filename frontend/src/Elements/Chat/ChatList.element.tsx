import { useRecoilState } from 'recoil'
import { chatListState } from '../../Atoms/Chat/Chat.atoms'
import ChatElement from './Chat.element'
import ChatSenderElement from './ChatSender.element'
import { useEffect } from 'react'

const ChatListElement = () => {
  const [chatList, setChatList] = useRecoilState(chatListState)

  useEffect(() => {
    const chatList = document.querySelector('.chatList') as HTMLDivElement
    chatList.scrollTop = chatList?.scrollHeight
  }, [chatList])

  return (
    <div className="w-3/12 border-border border-[1px] rounded-2xl p-6">
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
