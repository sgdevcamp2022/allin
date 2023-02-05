import { chatType } from '../../Atoms/Chat/Chat.atoms'

const ChatElement = ({ chat, className }: { chat: chatType; className?: string }) => {
  return (
    <div className={'flex justify-between break-all ' + className}>
      <div className="text-subText text-light">{chat.sender}</div>
      <div className="ml-4 text-mainText text-">{chat.content}</div>
    </div>
  )
}

export default ChatElement
