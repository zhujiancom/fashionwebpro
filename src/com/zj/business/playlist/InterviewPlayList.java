package com.zj.business.playlist;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.zj.business.po.Interview;
import com.zj.common.utils.XmlParse;

public class InterviewPlayList extends XmlParse {
	private List<Interview> interviews;
	
	public InterviewPlayList(List<Interview> interviews) {
		super();
		this.interviews = interviews;
	}

	@Override
	protected Document generateDocument() {
		Document doc = DocumentHelper.createDocument();
		Element rootElement = doc.addElement("playlist");
		rootElement.addAttribute("version", "1");
		rootElement.addAttribute("xmlns", "http://xspf.org/ns/0/");
		Element trackList = rootElement.addElement("trackList");
		for (int i = 0; i < interviews.size(); i++) {
			Interview interview = interviews.get(i);
			Element track = trackList.addElement("track");
			Element title = track.addElement("title");
			title.setText(interview.getInterviewEname());
			Element location = track.addElement("location");
			String videoUrl = interview.getInterviewurl();
			location.setText(videoUrl);
			Element image = track.addElement("image");
			String imageUrl = interview.getPoster();
			image.setText(imageUrl);
		}
		return doc;
	}

}
