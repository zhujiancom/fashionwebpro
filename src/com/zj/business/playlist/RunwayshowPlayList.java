package com.zj.business.playlist;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.zj.business.po.Runwayshow;
import com.zj.common.utils.XmlParse;

public class RunwayshowPlayList extends XmlParse {
	private List<Runwayshow> runwayshows;
	
	public RunwayshowPlayList(List<Runwayshow> runwayshows) {
		super();
		this.runwayshows = runwayshows;
	}


	@Override
	protected Document generateDocument() {
		Document doc = DocumentHelper.createDocument();
		Element rootElement = doc.addElement("playlist");
		rootElement.addAttribute("version", "1");
		rootElement.addAttribute("xmlns", "http://xspf.org/ns/0/");
		Element trackList = rootElement.addElement("trackList");
		for (int i = 0; i < runwayshows.size(); i++) {
			Runwayshow runwayshow = runwayshows.get(i);
			Element track = trackList.addElement("track");
			Element title = track.addElement("title");
			title.setText(runwayshow.getRunwayshowEname());
			Element location = track.addElement("location");
			String videoUrl = runwayshow.getRunwayshowUrl();
			location.setText(videoUrl);
			Element image = track.addElement("image");
			String imageUrl = runwayshow.getPoster();
			image.setText(imageUrl);
		}
		return doc;
	}

}
