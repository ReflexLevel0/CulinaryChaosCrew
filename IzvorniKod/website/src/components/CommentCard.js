import '../styles/CommentCard.css'
import React, {useEffect, useState} from "react";
import ApiHelper from "../ApiHelper";
import {Link} from "react-router-dom";

function formatTimestamp(timestamp) {
    const date = new Date(timestamp);

    const options = {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit'
    };

    return date.toLocaleDateString('en-US', options);
}

/* dodaj ovo
<Link to={}>
    <p>{username}</p>
</Link> */
function CommentCard({comment}){
    const [username, setUsername] = useState(null);

    useEffect(() => {
        const fetchUsername = async () => {
            try {
                // console.log(comment.uid)
                const fetchedUsername = await ApiHelper.GetUsernameFromUID(comment.uid);
                setUsername(fetchedUsername);
            } catch (error) {
                // Handle errors if needed
                console.error("Error fetching username:", error);
            }
        };

        fetchUsername();
    }, [comment.uid]);
    return(
        <div className={'comment-container'}>
            <p style={{ fontSize: '11px', margin: '0' }} className={"timestamp"}>{formatTimestamp(comment.timestamp)}</p>
            <div className="user-div">
                {username !== null ? (
                    <div className="user-container">
                        <img className="user-photo"
                             src={"https://www.pngkey.com/png/full/202-2024691_my-profile-comments-my-profile-icon-png.png"}
                             alt="user"/>
                        <div className="user-info">
                            <Link to={`/user/${username}`} rel="noopener noreferrer">
                                <p>{username}</p>
                            </Link>
                        </div>
                    </div>
                ) : (
                    <p className="loading-text">Loading user...</p>
                )}
            </div>
            <p className={"comment-text"}>{comment.text}</p>
        </div>

    )
}

export default CommentCard;