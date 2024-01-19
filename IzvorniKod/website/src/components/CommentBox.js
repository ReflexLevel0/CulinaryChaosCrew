import React, { useState } from "react";
import ApiHelper from "../ApiHelper";
import '../styles/CommentBox.css'

function CommentBox({rid, loggedIn}) {
    const [text, setText] = useState("");
    // event.preventDefault()
    const handleCommentSubmit = async () => {
        if (text.length === 0) {
            alert("Write something in the comment box!")
        }
        try {
            await ApiHelper.AddComment(rid, text);
            setText(""); // Clear the comment text after submission
        } catch (error) {
            console.error("Error adding comment:", error);
        }
    }; 

    return (
        loggedIn ? (
            <form>
                <textarea className={"box"}
                          placeholder="Leave a comment..."
                          value={text}
                          onChange={(e) => setText(e.target.value)}
                />
                <button id='submit' type="submit" onClick={handleCommentSubmit}>Add comment</button>
            </form>
        ) : null
    );
}

export default CommentBox;
