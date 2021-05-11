import React from "react";
import PropTypes from "prop-types";
import { DataGrid } from "@material-ui/data-grid";
import "./style.css";

const columns = [
  { field: "nickname", headerName: "Nickname", width: 220 },
  { field: "rank", headerName: "Rank", type: "number", width: 140 },
  { field: "kills", headerName: "Kills", type: "number", width: 140 },
  {
    field: "deaths",
    headerName: "Deaths",
    type: "number",
    width: 140,
  },
  {
    field: "totalMatches",
    headerName: "Total matches",
    type: "number",
    width: 180,
  },
  {
    field: "wonMatches",
    headerName: "Won matches",
    type: "number",
    width: 180,
  },
];

export default class PlayerStats extends React.Component {
  static propTypes = {
    stats: PropTypes.arrayOf(
      PropTypes.shape({
        fastcupId: PropTypes.number.isRequired,
        nickname: PropTypes.string.isRequired,
        rank: PropTypes.number.isRequired,
        kills: PropTypes.number.isRequired,
        deaths: PropTypes.number.isRequired,
        totalMatches: PropTypes.number.isRequired,
        wonMatches: PropTypes.number.isRequired,
      })
    ),
    statsNeedUpdate: PropTypes.bool.isRequired,
    loadAllStats: PropTypes.func.isRequired,
  };

  componentDidMount() {
    this.props.loadAllStats();
  }

  componentDidUpdate() {
    if (this.props.statsNeedUpdate) {
      this.props.loadAllStats();
    }
  }

  render() {
    const { stats } = this.props;

    return (
      <DataGrid
        className="table"
        rows={stats}
        columns={columns}
        pageSize={50}
        sortModel={[
          {
            field: "rank",
            sort: "desc",
          },
        ]}
      />
    );
  }
}
