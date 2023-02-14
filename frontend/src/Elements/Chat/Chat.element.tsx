import { useSetRecoilState } from 'recoil'
import { chatType } from '../../Atoms/Chat/Chat.atoms'
import {
  chatReportChatState,
  chatReportPopupedBooleanState,
} from '../../Atoms/Chat/ChatReport.atoms'
import { popupTitleTextStatus, popupedBooleanStatus } from '../../Atoms/Home/Popup.atoms'

const ChatElement = ({ chat, className }: { chat: chatType; className?: string }) => {
  const setChatReportChatState = useSetRecoilState(chatReportChatState)
  const setChatReportPopupState = useSetRecoilState(chatReportPopupedBooleanState)
  const setPopupTitle = useSetRecoilState(popupTitleTextStatus)
  const setPopuped = useSetRecoilState(popupedBooleanStatus)

  const onChatClick = () => {
    const storage = localStorage.getItem('isAutoLogin') === 'true' ? localStorage : sessionStorage
    if (storage.getItem('isLogined') !== 'true') {
      setPopupTitle('로그인을 해야\n신고할 수 있습니다.')
      setPopuped(true)
      return
    }
    setChatReportChatState(chat)
    setChatReportPopupState(true)
  }

  return (
    <button className={'flex break-all ' + className} onClick={onChatClick}>
      <div className="text-subText text-light">{chat.sender}</div>
      <div className="ml-4 text-mainText text-">{chat.content}</div>
    </button>
  )
}

export default ChatElement
