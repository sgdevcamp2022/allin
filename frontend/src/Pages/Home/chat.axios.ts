import { chatReportType } from '../../Atoms/Chat/ChatReport.atoms'
import Axios from '../../utils/axios'

export const chatReport = (chatReportChat: chatReportType) => {
  try {
    Axios.post(import.meta.env.VITE_CHAT_SERVER_URL + '/api/v1/reports', chatReportChat)
      .then((res) => {
        if (res.status !== 200) {
          throw new Error('Can not report chat reason: ' + res.status)
        }
        if (res.data.result === 'success') {
          throw new Error('Can not report chat reason: ' + res.data.result)
        }
      })
      .catch((err) => {
        throw new Error('Cant not report chat reason: ' + err.message)
      })
  } catch (err) {
    alert('예기치 못한 에러가 있습니다')
    console.dir(err)
    return
  }
}
