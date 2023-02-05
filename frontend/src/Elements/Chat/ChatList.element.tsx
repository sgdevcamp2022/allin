import { useRecoilState } from 'recoil'
import { chatListState } from '../../Atoms/Chat/Chat.atoms'
import ChatElement from './Chat.element'
import ChatSenderElement from './ChatSender.element'

const ChatListElement = () => {
  const [chatList, setChatList] = useRecoilState(chatListState)

  return (
    <div className="w-1/5">
      {chatList.map((chat) => (
        <ChatElement chat={chat} className="w-full" />
      ))}
      <ChatSenderElement />
    </div>
  )
}

export default ChatListElement
