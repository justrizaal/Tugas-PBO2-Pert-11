<?php 
error_reporting(E_ALL);
ini_set('display_errors', 1);

include "config/koneksi.php";
?>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- CSS -->
    <link rel="stylesheet" href="css/style.css">
    
    <title>LAPORAN BARANG MASUK</title>

    <!-- Print CSS -->
    <style>
      @media print {
        body * {
          visibility: hidden;
        }
        .printable, .printable * {
          visibility: visible;
        }
        .printable {
          position: absolute;
          left: 0;
          top: 0;
          width: 100%;
        }
        .non-printable {
          display: none;
        }
      }
    </style>
  </head>
  <body>
    <div class="container text-center non-printable">
      <a href="javascript:window.print()"><img src="img/print.jpg" alt="print" class="img-thumbnail" height="20" width="40" style="margin-top:5px"></a>
      <div><?php echo date('Y-m-d'); ?></div>
    </div>
    <div class="container printable">
      <div class="row">
        <div class="col-md-12">
          <h1 class="text-center text-primary">LAPORAN BARANG MASUK</h1>
        </div>
      </div>
      <form method="post" class="form-inline justify-content-center form-dates non-printable">
        <div class="form-group mx-sm-3">
          <label for="dari" class="mr-2">Dari Tanggal</label>
          <input type="date" id="dari" name="dari" class="form-control">
        </div>
        <div class="form-group mx-sm-3">
          <label for="sampai" class="mr-2">Sampai</label>
          <input type="date" id="sampai" name="sampai" class="form-control">
        </div>
        <button type="submit" name="cek" class="btn btn-primary">CEK</button>
      </form>
      <br>
      <div class="row">
        <div class="col-md-12">
          <table class="table table-striped">
            <thead class="thead-dark">
              <tr>
                <th>Kode Barang</th>
                <th>Nama Barang</th>
                <th>Jumlah Barang</th>
                <th>Harga Beli</th>
                <th>Harga Jual</th>
                <th>Tanggal Masuk</th>
              </tr>
            </thead>
            <tbody>
              <?php 
                $where = '';
                if (isset($_POST['cek']) && !empty($_POST['dari']) && !empty($_POST['sampai'])) {
                  $dari = $_POST['dari'];
                  $sampai = $_POST['sampai'];
                  $where = "WHERE tanggal_masuk BETWEEN '$dari' AND '$sampai'";
                }

                $sql = "SELECT * FROM tbl_barang $where";
                $query = mysqli_query($con, $sql);

                if ($query) {
                  while($r = mysqli_fetch_array($query)){
              ?>
              <tr>
                <td><?php echo htmlspecialchars($r['kd_barang']); ?></td>
                <td><?php echo htmlspecialchars($r['nama_barang']); ?></td>
                <td><?php echo htmlspecialchars($r['jumlah_barang']); ?></td>
                <td><?php echo htmlspecialchars($r['harga_beli']); ?></td>
                <td><?php echo htmlspecialchars($r['harga_jual']); ?></td>
                <td><?php echo htmlspecialchars($r['tanggal_masuk']); ?></td>
              </tr>
              <?php 
                  } 
                } else {
                  echo "<tr><td colspan='6' class='text-center'>No data found</td></tr>";
                }
              ?>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>
