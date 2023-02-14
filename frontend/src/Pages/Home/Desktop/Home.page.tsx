import NavbarElement from '../../../Elements/Navbar/Navbar.element'
import ChatListElement from '../../../Elements/Chat/ChatList.element'
import VideoElement from '../../../Elements/Video/Video.element'
import PopupElement from '../../../Elements/Popup/Popup.element'
import ChatReportPopupElement from '../../../Elements/Popup/ChatReportPopup.element'

const HomeDesktopPage = () => {
  return (
    <div className="min-w-[80rem] w-screen">
      <PopupElement />
      <div className="h-24"></div>
      <NavbarElement />
      <div className="flex w-full px-20 justify-between items-start">
        <div className="w-4/5 ">
          <VideoElement />
        </div>
        <div className="w-3/12">
          <ChatReportPopupElement />
          <ChatListElement />
        </div>
      </div>
    </div>
  )
}

export default HomeDesktopPage
