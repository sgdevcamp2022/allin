/// <reference types="vite/client" />
interface ImportEnv {
  readonly VITE_CHAT_SERVER_URL: string
  readonly VITE_AUTH_SERVER_URL: string
}

interface Import {
  readonly env: ImportEnv
}
