import { useRecoilState, useRecoilValue } from 'recoil'
import {
  CHAT_REPORT_REASON,
  chatReportPopupedBooleanState,
  chatReportState,
} from '../../Atoms/Chat/ChatReport.atoms'
import { chatReport } from '../../Pages/Home/chat.axios'

const ChatReportPopupElement = () => {
  const reportReason = Object.keys(CHAT_REPORT_REASON)
  const [isPopuped, setIsPopuped] = useRecoilState(chatReportPopupedBooleanState)
  const chatReportChat = useRecoilValue(chatReportState)

  const onReportClick = (report: string) => {
    chatReport(chatReportChat)
    setIsPopuped(false)
  }

  return (
    <>
      {isPopuped ? (
        <div className="w-full flex justify-center items-center drop-shadow">
          <div className="absolute w-full h-[39.5rem] flex justify-center items-center translate-y-[50%] backdrop-blur-sm z-30 bg-[#999999] bg-opacity-50 rounded-xl">
            <div className="flex flex-col gap-2">
              {reportReason.map((reason, index) => (
                <button
                  onClick={() => {
                    onReportClick(reason)
                  }}
                  className="bg-black px-4 py-2 text-mainTextWhite rounded-md"
                  key={index}
                >
                  {CHAT_REPORT_REASON[reason]}
                </button>
              ))}
            </div>
          </div>
        </div>
      ) : (
        ''
      )}
    </>
  )
}

export default ChatReportPopupElement
