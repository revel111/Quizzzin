document.addEventListener('DOMContentLoaded', () => {
    window.addComment = async function (event) {
        event.preventDefault();
        const content = document.getElementById('comment-content').value;
        const idUser = document.getElementById('user-id').value;
        const idPuzzle = document.getElementById('puzzle-id').value;

        const response = await fetch('/quizzzin/comments', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({text: content, idUser, idPuzzle})
        });

        if (response.ok)
            addCommentToDOM(await response.json())
        else
            alert('Failed to add comment.');
    }

    function addCommentToDOM(comment) {
        const commentList = document.getElementById('comments-list');

        const newCommentElement = document.createElement('li');
        newCommentElement.setAttribute('data-id', comment.id);

        newCommentElement.innerHTML = `
        <p>${comment.username}: at ${comment.dateOfAdding}</p>
        <p class="comment-text">${comment.text}</p>
        <button onclick="editComment(this)">Edit</button>
        <button onclick="deleteComment(this)">Delete</button>
    `;
        commentList.appendChild(newCommentElement);
    }

    window.deleteComment = async function (button) {
        const commentElement = button.closest('li');
        const commentId = commentElement.getAttribute('data-id');

        const response = await fetch(`/quizzzin/comments/${commentId}`, {
            method: 'DELETE',
        });

        if (response.ok)
            commentElement.remove();
        else
            alert('Failed to delete comment.');
    }

    window.editComment = async function (button) {
        const commentElement = button.closest('li');
        const commentId = commentElement.getAttribute('data-id');
        const commentContentElement = commentElement.querySelector('.comment-text')
        const originalContent = commentContentElement.innerText;

        const newContent = prompt('Edit your comment:', originalContent);
        if (newContent && newContent !== originalContent)
            updateComment(commentId, newContent).then(success => {
                if (success)
                    commentContentElement.innerText = newContent;
                else
                    alert('Failed to update comment');
            });
    }

    async function updateComment(commentId, content) {
        const response = await fetch(`/quizzzin/comments/${commentId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({content})
        });

        return response.ok;
    }
});