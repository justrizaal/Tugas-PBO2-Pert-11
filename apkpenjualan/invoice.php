<?php 
error_reporting(E_ALL);
ini_set('display_errors', 1);

include "config/koneksi.php";
?>

<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>FAKTUR PENJUALAN</title>

    <style>
    .invoice-box {
        max-width: 800px;
        margin: 20px auto;
        padding: 20px;
        border: 2px solid #eee;
        box-shadow: 0 0 10px rgba(0, 0, 0, .15);
        font-size: 16px;
        line-height: 1.6;
        font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;
        color: #555;
    }

    .invoice-box table {
        width: 100%;
        text-align: left;
        border-collapse: collapse;
    }

    .invoice-box table td {
        padding: 10px;
        vertical-align: top;
        border: 1px solid #eee;
    }

    .invoice-box table tr.heading td {
        background: #f7f7f7;
        font-weight: bold;
    }

    .invoice-box table tr.total td {
        background: #f7f7f7;
        font-weight: bold;
        text-align: right;
    }

    .invoice-box table tr.item td {
        border-bottom: 1px solid #eee;
    }

    .invoice-box table tr.item.last td {
        border-bottom: none;
    }
</style>
</head>

<body onload="window.print()">

    <div class="invoice-box">
        <table>
            <tr class="top">
                <td colspan="5">
                    <table>
                        <tr>
                        <td class="title" style="font-size: 28px; font-weight: bold; color: #007bff; text-align: center;">
                            INVOICE PENJUALAN
                        </td>
                            <td>
                                <?php
                                $tanggal = date('Y-m-d');
                                echo $tanggal;
                                ?>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>

            <tr class="information">
                <td colspan="5">
                    <table>
                        <tr>
                            <td>
                                Top Deal Electronic ABC<br>
                                Jl. ABC No.63, <br>
                                Kota Bandung, Jawa Barat 40235
                            </td>

                            <td>
                                Kasir : Whatzitooya<br>
                                22552011109<br>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>

            <tr class="heading">
                <td>
                    No. Faktur:
                </td>

                <td>
                    <?php echo htmlspecialchars($_GET['fk']); ?>
                </td>
            </tr>

            <tr class="details">
                <td></td>
                <td></td>
            </tr>

            <tr class="heading">
                <td>No.</td>
                <td>Nama Barang</td>
                <td>Harga Satuan</td>
                <td>Jumlah Beli</td>
                <td>Total Harga</td>
            </tr>

            <?php
            // Sanitasi input
            $nofaktur = mysqli_real_escape_string($con, $_GET['fk']);

            // Ambil data dari database
            $no = 0;
            $result = mysqli_query($con, "SELECT * FROM tbl_penjualan WHERE nofaktur = '$nofaktur'");
            if ($result) {
                while ($r = mysqli_fetch_array($result)) {
                    $no++;
            ?>
                    <tr class="item">
                        <td><?php echo $no; ?></td>
                        <td><?php echo htmlspecialchars($r['nama_barang']); ?></td>
                        <td><?php echo htmlspecialchars($r['hsatuan']); ?></td>
                        <td><?php echo htmlspecialchars($r['jumlah_jual']); ?></td>
                        <td><?php echo htmlspecialchars($r['harga']); ?></td>
                    </tr>
            <?php
                }
            } else {
                echo "Query failed: " . mysqli_error($con);
            }
            ?>

            <tr class="spacer">
                <td colspan="5"></td>
            </tr>

            <tr class="total">
                <td></td>
                <td></td>
                <td></td>
                <th scope="row">Grand Total :</th>
                <?php
                $result = mysqli_query($con, "SELECT SUM(harga) as TOTAL FROM tbl_penjualan WHERE nofaktur='$nofaktur'");
                $total = mysqli_fetch_array($result);
                $grand_total = $total['TOTAL'] ?? 0;
                ?>
                <td><?php echo htmlspecialchars($grand_total); ?></td>
            </tr>

            <tr class="total">
                <td></td>
                <td></td>
                <td></td>
                <th scope="row">Bayar :</th>
                <?php
                $result = mysqli_query($con, "SELECT bayar FROM tbl_penjualan WHERE nofaktur='$nofaktur'");
                $bayar = mysqli_fetch_array($result);
                $bayar_amount = $bayar['bayar'] ?? 0;
                ?>
                <td><?php echo htmlspecialchars($bayar_amount); ?></td>
            </tr>

            <tr class="total">
                <td></td>
                <td></td>
                <td></td>
                <th scope="row">Kembalian :</th>
                <?php
                $result = mysqli_query($con, "SELECT kembalian FROM tbl_penjualan WHERE nofaktur='$nofaktur'");
                $kembalian = mysqli_fetch_array($result);
                $kembalian_amount = $kembalian['kembalian'] ?? 0;
                ?>
                <td><?php echo htmlspecialchars($kembalian_amount); ?></td>
            </tr>
        </table>
    </div>
</body>

</html>
