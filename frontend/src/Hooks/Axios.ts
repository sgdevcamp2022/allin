import { useEffect, useState } from 'react'
import axios from 'axios'

type AxiosMethod = 'get' | 'post' | 'put' | 'delete'

const useAxios = (initalUrl: string, method: AxiosMethod) => {
  const [url, _] = useState(initalUrl)
  const [value, setValue] = useState('')

  useEffect(() => {
    fetchData()
  }, [url])
  const fetchData = () => getAxiosByMethod(url, method).then(({ data }) => setValue(data))

  return [value]
}

const getAxiosByMethod = (url: string, method: AxiosMethod) => {
  switch (method) {
    case 'get':
      return axios.get(url)
    case 'post':
      return axios.post(url)
    case 'put':
      return axios.put(url)
    case 'delete':
      return axios.delete(url)
  }
}

export default useAxios
export {}
