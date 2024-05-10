import useSWR from 'swr'
import { BoardResType, ErrorResponce } from '../../lib/types'
import fetcher from '../../lib/fetcher'

export const useBoardInfo = (board_id: string) => {
  const {
    data: boardData,
    error,
    isValidating: isLoading
  } = useSWR<BoardResType, ErrorResponce>(
    board_id ? `/api/board/${board_id}` : null,
    fetcher
  )
  return { boardData, isLoading, error }
}

export const useBoardList = () => {
  const {
    data: boards,
    error,
    isValidating: isLoading
  } = useSWR<BoardResType, ErrorResponce>('/api/board', fetcher)
  return { boards, isLoading, error }
}
