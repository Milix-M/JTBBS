import useSWR from 'swr'
import { CommentType, ErrorResponce } from '../../lib/types'
import fetcher from '../../lib/fetcher'

export const useCommentInfo = (comment_id: string) => {
  const {
    data: commentData,
    error,
    isValidating: isLoading
  } = useSWR<CommentType, ErrorResponce>(
    comment_id ? `/api/comment/${comment_id}` : null,
    fetcher
  )

  return { commentData, isLoading, error }
}
