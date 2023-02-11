import { useRecoilValue, useSetRecoilState } from 'recoil'
import { chatBlockBooleanState, chatSendStringState } from '../../Atoms/Chat/Chat.atoms'
import InputElement from '../Input/Input.element'
import { useEffect, useState } from 'react'
import { PopupedBooleanStatus, popupTitleTextStatus } from '../../Atoms/Home/Popup.atoms'

const ChatSenderElement = () => {
  const chatText = useRecoilValue(chatSendStringState)
  const isChatBlock = useRecoilValue(chatBlockBooleanState)
  const [sendImgColor, setSendImageColor] = useState('#7C7C7C')
  const [borderColor, setBorderColor] = useState('border-subText')
  const [placeholder, setPlaceholder] = useState('메시지 입력')
  const [placeholderColor, setPlaceholderColor] = useState('placeholder:text-subText')
  const [chatDisabled, setChatDisabled] = useState(false)
  const [bgColor, setBgColor] = useState('bg-hidden')
  const setPopupTitle = useSetRecoilState(popupTitleTextStatus)
  const setPopuped = useSetRecoilState(PopupedBooleanStatus)

  useEffect(() => {
    if (isChatBlock) {
      setSendImageColor('#F15E79')
      setBorderColor('border-warningText')
      setPlaceholderColor('placeholder:text-warningText')
      setBgColor('bg-warning')
      setPlaceholder('차단된 상태입니다')
      setChatDisabled(true)
      return
    }
    if (chatText != '') {
      setSendImageColor('#6F21D2')
      setBorderColor('border-point')
      return
    }
    setSendImageColor('#7C7C7C')
    setBorderColor('border-subText')
  }, [chatText, isChatBlock])

  const chatFocused = () => {
    const storage = localStorage.getItem('isAutoLogin') === 'true' ? localStorage : sessionStorage
    if (storage.getItem('isLogined') !== 'true') {
      setPopupTitle('로그인을 해야\n채팅할 수 있습니다.')
      setPopuped(true)
    }
  }

  return (
    <div
      className={
        bgColor + ' w-full px-5 py-3 flex items-center rounded-full border-[1px] ' + borderColor
      }
    >
      <InputElement
        className={bgColor + ' w-full  ' + placeholderColor}
        textState={chatSendStringState}
        placeholder={placeholder}
        disabled={chatDisabled}
        onFocus={chatFocused}
      />
      <button>
        <svg
          width="20"
          height="20"
          viewBox="0 0 20 20"
          fill="none"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            d="M11.9207 20C12.6569 20 13.1666 19.4054 13.5158 18.4993L19.7452 2.19915C19.9056 1.78386 20 1.41576 20 1.09486C20 0.424729 19.5753 0 18.8957 0C18.5842 0 18.2067 0.0849457 17.7914 0.245399L1.4252 6.51251C0.613497 6.82397 0 7.33365 0 8.06984C0 8.96649 0.670127 9.30628 1.59509 9.58943L6.48419 11.0807C7.14488 11.2883 7.53186 11.2789 7.99434 10.8447L18.5087 1.10429C18.6409 0.981595 18.8013 1.00047 18.9051 1.09486C19.009 1.18924 19.009 1.35913 18.8957 1.48183L9.17414 12.0151C8.77773 12.4587 8.73997 12.874 8.93818 13.5347L10.3917 18.3294C10.6748 19.2921 11.0146 20 11.9207 20Z"
            fill={sendImgColor}
          />
        </svg>
      </button>
    </div>
  )
}

export default ChatSenderElement
