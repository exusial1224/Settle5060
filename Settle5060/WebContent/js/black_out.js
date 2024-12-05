   $(document).ready(function () {


        //ポップアップイン
        function showPopup() {
            populatePopupDateSelect(); //リスト更新
            $("#blackout").fadeIn();
            $("#popup").fadeIn();
        }

        //ポップアップアウト
        function hidePopup() {
            $("#blackout").fadeOut();
            $("#popup").fadeOut();
        }

        //ポップアップリスト
        function populatePopupDateSelect() {
            const $dateSelect = $("#popupDateSelect");
            $dateSelect.empty(); // リストをクリア
            const currentDate = new Date();
            const twoMonthsLater = new Date();
            twoMonthsLater.setMonth(currentDate.getMonth() + 2);

            while (currentDate <= twoMonthsLater) {
                const dateValue = currentDate.toISOString().split("T")[0];
                $dateSelect.append(
                    $("<option>").val(dateValue).text(dateValue)
                );
                currentDate.setDate(currentDate.getDate() + 1);
            }
        }

        //確定ボタン
        $("#popupSubmit").on("click", function () {
            const selectedDate = $("#popupDateSelect").val();
            if (!selectedDate) {
                alert("日付を選択してください。");
                return;
            }

            //日付を入力フォームに追加
            $("#dateSelect").val(selectedDate);
            hidePopup();
        });

        //キャンセルボタン
        $("#popupCancel").on("click", function () {
            hidePopup();
        });

        //ドロップダウン生成
        function populateDateSelect() {
            const $dateSelect = $("#dateSelect");
            const currentDate = new Date();
            const twoMonthsLater = new Date();
            twoMonthsLater.setMonth(currentDate.getMonth() + 2);

            while (currentDate <= twoMonthsLater) {
                const dateValue = currentDate.toISOString().split("T")[0];
                $dateSelect.append(
                    $("<option>").val(dateValue).text(dateValue)
                );
                currentDate.setDate(currentDate.getDate() + 1);
            }
        }

        //初期化
        populateDateSelect(); //元のドロップダウンを更新
        $("#dateSelect").on("click", showPopup); //ポップアップ表示
    });
