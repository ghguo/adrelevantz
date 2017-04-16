var f;
function revivefn(d, c, cq) {
	var a = "<?php echo $etag; ?>";
	c.reviveAsync = c.reviveAsync || {};
	c.cq = cq;
	try {
		if (!c.reviveAsync.hasOwnProperty(a)) {
			f = c.reviveAsync[a] = {
				id: Object.keys(c.reviveAsync).length,
				name: "<?php echo $product; ?>",
				start: function () {
					var e = function () {
						try {
							if (!f.done) {
								d.removeEventListener("DOMContentLoaded", e, false);
								c.removeEventListener("load", e, false);
								f.done = true;
							}
							if (c.cq != "") {
								f.apply(f.detect(), c.cq)
							} else {
								f.apply(f.detect())
							}
						} catch (g) {
							console.log(g)
						}
					};
					if (d.readyState === "complete") {
						setTimeout(e)
					} else {
						d.addEventListener("DOMContentLoaded", e, false);
						c.addEventListener("load", e, false)
					}
				},
				ajax: function (e, g) {
					var h = new XMLHttpRequest();
					h.onreadystatechange = function () {
						if (this.readyState == 4) {
							if (this.status == 200) {
								f.spc(JSON.parse(this.responseText))
							}
						}
					};
					h.open("POST", e, true);
					h.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
					h.withCredentials = true;
					h.send(f.encode(g).join("&"))
				},
				encode: function (m, n) {
					var e = [],
					h,
					i;
					for (h in m) {
						if (m.hasOwnProperty(h)) {
							var l = n ? n + "[" + h + "]" : h;
							if ((/string|number|boolean/).test(typeof m[h])) {
								e.push(encodeURIComponent(l) + "=" + encodeURIComponent(m[h]))
							} else {
								var g = f.encode(m[h], l);
								for (i in g) {
									e.push(g[i])
								}
							}
						}
					}
					return e
				},
				apply: function (g) {
					if (g.zones.length) {
						var e = d.location.protocol == "http:" ? "<?php echo MAX_commonConstructDeliveryUrl('asyncspc.php'); ?>" : "<?php echo MAX_commonConstructSecureDeliveryUrl('asyncspc.php'); ?>";
						g.zones = g.zones.join("|");
						g.promoters = g.promoters.join("|");
						g.loc = d.location.href;
						if (d.referrer) {
							g.referer = d.referrer
						}
						if (arguments.length > 1) {
							g.q = arguments[1];
							if (arguments.length > 2) {
								g.promo = arguments[2];
							}
						}
						f.ajax(e, g)
					}
				},
				detect: function () {
					var o = d.querySelectorAll("ins[data-" + f.name + "-id='" + a + "']");
					var n = {
						zones: [],
						promoters: [],
						prefix: f.name + "-" + f.id + "-"
					};
					for (var g = 0; g < o.length; g++) {
						var k = o[g];
						if (k.hasAttribute("data-" + f.name + "-zoneid")) {
							var l = new RegExp("^data-" + f.name + "-(.*)$"),
							e;
							for (var h = 0; h < k.attributes.length; h++) {
								if (e = k.attributes[h].name.match(l)) {
									if (e[1] == "zoneid") {
										n.zones[g] = k.attributes[h].value;
										k.id = n.prefix + g
									} else if (e[1] == "promoter") {
										n.promoters[g] = k.attributes[h].value;
									} else {
										if (e[1] != "id") {
											n[e[1]] = k.attributes[h].value
										}
									}
								}
							}
						}
					}
					return n
				},
				createFrame: function (h) {
					var e = d.createElement("IFRAME"),
					g = e.style;
					e.scrolling = "no";
					e.frameBorder = 0;
					e.width = h.width > 0 ? h.width : 0;
					e.height = h.height > 0 ? h.height : 0;
					g.border = 0;
					g.overflow = "hidden";
					return e
				},
				loadFrame: function (g, e) {
					var h = g.contentDocument || g.contentWindow.document;
					h.open();
					h.writeln("<!DOCTYPE html>");
					h.writeln("<html>");
					h.writeln('<head><base target="_top"></head>');
					h.writeln('<body border="0" margin="0" style="margin: 0; padding: 0">');
					h.writeln(e);
					h.writeln("</body>");
					h.writeln("</html>");
					h.close()
				},
				spc: function (k) {
					for (var e in k) {
						if (k.hasOwnProperty(e)) {
							var o = k[e];
							var n = d.getElementById(e);
							if (n) {
								if (o.iframeFriendly) {
									var l = f.createFrame(o);
									n.appendChild(l);
									f.loadFrame(l, o.html)
								} else {
									n.innerHTML = o.html;
									var g = n.getElementsByTagName("SCRIPT");
									for (var l = 0; l < g.length; l++) {
										var q = document.createElement("SCRIPT");
										var p = g[l].attributes;
										for (var h = 0; h < p.length; h++) {
											q[p[h].nodeName] = p[h].value
										}
										if (g[l].innerHTML) {
											q.text = g[l].innerHTML
										}
										g[l].parentNode.replaceChild(q, g[l])
									}
								}
							}
						}
					}
				}
			};
		}
		f.start()
	} catch (b) {
		if (console.log) {
			console.log(b)
		}
	}
};
