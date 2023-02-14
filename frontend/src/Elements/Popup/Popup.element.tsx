import { useRecoilState, useRecoilValue } from 'recoil'
import {
  popupedBooleanStatus,
  popupSubTitleTextStatus,
  popupTitleTextStatus,
} from '../../Atoms/Home/Popup.atoms'
import { useEffect, useState } from 'react'

const PopupElement = () => {
  const [popuped, setPopuped] = useRecoilState(popupedBooleanStatus)
  const title = useRecoilValue(popupTitleTextStatus)
  const subtitle = useRecoilValue(popupSubTitleTextStatus)
  const [popupDisplay, setPopupDisplay] = useState('hidden')

  const onClose = () => {
    setPopuped(false)
  }

  useEffect(() => {
    popuped ? setPopupDisplay('block') : setPopupDisplay('hidden')
  }, [popuped])

  return (
    <div
      className={
        'h-screen w-screen absolute top-0 left-0 z-50 flex justify-center items-center backdrop-blur-sm  ' +
        popupDisplay
      }
    >
      <div className="bg-white border-2 border-border py-12 px-24 rounded-xl flex flex-col text-center items-center">
        <div className="font-medium text-mainText p-4 text-lg">
          {title.split('\n').map((t) => (
            <div>{t}</div>
          ))}
        </div>
        <div className="pb-4 font-light text-subText text-sm">
          {subtitle.split('\n').map((t) => (
            <div>{t}</div>
          ))}
        </div>
        <button className="bg-point text-white w-fit py-1 px-6 rounded-md" onClick={onClose}>
          확인
        </button>
      </div>
    </div>
  )
}

export default PopupElement
