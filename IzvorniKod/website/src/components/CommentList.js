import React, {useEffect, useState} from "react";
import ApiHelper from "../ApiHelper";
import CommentCard from "./CommentCard";
import '../styles/CommentList.css'
import CommentBox from "./CommentBox";


function CommentList({rid, loggedIn}) {
    const [comments, setComments] = useState([]);

    useEffect(() => {
        const fetchComments = async () => {
            try {
                const commentsData = await ApiHelper.GetComments(rid);
                const sortedComments = commentsData.sort((a, b) => new Date(b.timestamp) - new Date(a.timestamp));
                setComments(sortedComments);
                // setComments(commentsData);
            } catch (error) {
                console.error('Error fetching comments:', error);
            }
        };
        fetchComments();
    }, []);


    return (
        <div className="comment-cards-container">
            {comments.map((comment) => (
                <CommentCard comment={comment}/>
            ))}

            <div className={"c-box"}>
            <CommentBox rid={rid} loggedIn={loggedIn}/>
            </div>
        </div>
    );
}

export default CommentList;
