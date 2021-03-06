package cz.inventi.inventiskeleton.presentation.post.list

import com.hannesdorfmann.mosby3.mvp.MvpNullObjectBasePresenter
import cz.inventi.inventiskeleton.data.post.Post
import cz.inventi.inventiskeleton.domain.post.GetPostListUseCase
import cz.inventi.inventiskeleton.presentation.common.PresentationObserver
import javax.inject.Inject

/**
 * Created by semanticer on 05.05.2017.
 */

class PostListPresenter @Inject constructor(private val useCase: GetPostListUseCase) : MvpNullObjectBasePresenter<PostListView>() {

    override fun attachView(view: PostListView) {
        super.attachView(view)
        reloadList()
    }

    override fun detachView(retainInstance: Boolean) {
        useCase.dispose()
    }

    fun reloadList() {
        useCase.execute(PostListObserver(view), GetPostListUseCase.Params(limit = 20))
    }

    fun onPostSelected(post: Post) {
        view.showDetailPost(post.id)
    }

    fun onPostLongClicked(post: Post) : Boolean {
        view.deletePost(post)
        return false
    }

    fun onAddPost() {
        view.showAddPost()
    }

    class PostListObserver constructor(view: PostListView): PresentationObserver<List<Post>, PostListView>(view) {
        override fun onNext(list: List<Post>) {
            onView { it.showPostList(list) }
        }
    }

}
