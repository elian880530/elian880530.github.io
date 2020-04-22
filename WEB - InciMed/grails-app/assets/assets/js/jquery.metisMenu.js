;(function ($, window, document, undefined) {

    var pluginName = "metisMenu",
        defaults = {
            toggle: true,
            cookieName: "MetisMenuState"
        };

    function Plugin(element, options) {
        this.element = element;
        this.settings = $.extend({}, defaults, options);
        this._defaults = defaults;
        this._name = pluginName;
        this.init();
    }

    Plugin.prototype = {
        init: function () {

            var $this = $(this.element),
                $toggle = this.settings.toggle,
                $cookieName = this.settings.cookieName;

            //Restores the menu state from cookies
            var documentCookie = document.cookie;
            $this.find('li').has('ul').each(function (i) {
                var pos = documentCookie.indexOf($cookieName + "_" + i + "=");
                if (pos > -1) {
                    documentCookie.substr(pos).split('=')[1].indexOf('false') ? $(this).addClass('active') : $(this).removeClass('active');
                }
            });

            if (this.isIE() <= 9) {
                $this.find('li.active').has('ul').children('ul').collapse('show');
                $this.find('li').not('.active').has('ul').children('ul').collapse('hide');
            } else {
                $this.find('li.active').has('ul').children('ul').addClass('collapse in');
                $this.find('li').not('.active').has('ul').children('ul').addClass('collapse');
            }

            $this.find('li').has('ul').children('a').on('click', function (e) {
                e.preventDefault();

                $(this).parent('li').toggleClass('active').children('ul').collapse('toggle');

                if ($toggle) {
                    $(this).parent('li').siblings().removeClass('active').children('ul.in').collapse('hide');
                }

                //Stores the menu state in cookies
                $this.find('li').has('ul').each(function (i) {
                    document.cookie = $cookieName + "_" + i + "=" + $(this).hasClass('active');
                });
            });
        },

        isIE: function () {//https://gist.github.com/padolsey/527683
            var undef,
                v = 3,
                div = document.createElement('div'),
                all = div.getElementsByTagName('i');

            while (
                div.innerHTML = '<!--[if gt IE ' + (++v) + ']><i></i><![endif]-->',
                    all[0]
                ) ;
            return v > 4 ? v : undef;
        }
    };

    $.fn[pluginName] = function (options) {
        return this.each(function () {
            if (!$.data(this, "plugin_" + pluginName)) {
                $.data(this, "plugin_" + pluginName, new Plugin(this, options));
            }
        });
    };

})(jQuery, window, document);