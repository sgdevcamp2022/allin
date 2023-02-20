import axios from 'axios'

const instance = axios.create()

instance.interceptors.request.use(
  async (config) => {
    let accessToken = localStorage.getItem('accessToken') ?? ''

    try {
      if (accessToken && accessToken != '') {
        config.headers.Authorization = `Bearer ${accessToken}`
      }
    } catch (err) {
      console.error('[_axios.interceptors.request] config : ' + err)
    }

    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

instance.interceptors.response.use(
  (response) => {
    return Promise.resolve(response)
  },

  (error) => {
    return Promise.reject(error)
  }
)

export default instance
