import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import articlesApi from "../api/articlesApi";
import Article from "../components/Article";

export default function ArticlePage() {
  const { articleId } = useParams();
  const [article, setArticle] = useState({});

  useEffect(() => {
    async function fetchArticle() {
      const response = await articlesApi.getArticle(articleId);
      setArticle(response);
    }
    fetchArticle();
  }, []);

  return (
    <div>
      <Article key={article.id} article={article} isDetail={true}></Article>
    </div>
  );
}
